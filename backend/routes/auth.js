import express from "express"
import bcrypt from "bcryptjs"
import jwt from "jsonwebtoken"
import db from "../db/sqlite.js"
import { JWT_SECRET } from "../middleware/auth.js"

const router = express.Router()

// Register
router.post("/register", async (req, res) => {
  try {
    const { email, password, name } = req.body
    if (!email || !password || !name) {
      return res.status(400).json({ error: "All fields are required" })
    }

    const existingUser = db
      .prepare("SELECT * FROM users WHERE email = ?")
      .get(email)

    if (existingUser) {
      return res.status(400).json({ error: "Email already exists" })
    }

    const hashedPassword = await bcrypt.hash(password, 10)
    const id = Date.now().toString()

    db.prepare(`
      INSERT INTO users (id, email, password, name, role, createdAt)
      VALUES (?, ?, ?, ?, ?, ?)
    `).run(id, email, hashedPassword, name, "customer", new Date().toISOString())

    const token = jwt.sign({ id, email, role: "customer" }, JWT_SECRET, {
      expiresIn: "7d",
    })

    res.status(201).json({
      token,
      user: { id, email, name, role: "customer" },
    })
  } catch {
    res.status(500).json({ error: "Server error" })
  }
})

// Login
router.post("/login", async (req, res) => {
  try {
    const { email, password } = req.body

    const user = db
      .prepare("SELECT * FROM users WHERE email = ?")
      .get(email)

    if (!user || !(await bcrypt.compare(password, user.password))) {
      return res.status(400).json({ error: "Invalid credentials" })
    }

    const token = jwt.sign(
      { id: user.id, email: user.email, role: user.role },
      JWT_SECRET,
      { expiresIn: "7d" }
    )

    res.json({
      token,
      user: { id: user.id, email: user.email, name: user.name, role: user.role },
    })
  } catch {
    res.status(500).json({ error: "Server error" })
  }
})

export default router
