import express from "express"
import bcrypt from "bcryptjs"
import jwt from "jsonwebtoken"
import { readJSON, writeJSON } from "../utils/db.js"
import { JWT_SECRET } from "../middleware/auth.js"

const router = express.Router()

// Register
router.post("/register", async (req, res) => {
  try {
    const { email, password, name } = req.body

    if (!email || !password || !name) {
      return res.status(400).json({ error: "All fields are required" })
    }

    const users = await readJSON("users.json")

    if (users.find((u) => u.email === email)) {
      return res.status(400).json({ error: "Email already exists" })
    }

    const hashedPassword = await bcrypt.hash(password, 10)
    const newUser = {
      id: Date.now().toString(),
      email,
      password: hashedPassword,
      name,
      role: "customer",
      createdAt: new Date().toISOString(),
    }

    users.push(newUser)
    await writeJSON("users.json", users)

    const token = jwt.sign({ id: newUser.id, email: newUser.email, role: newUser.role }, JWT_SECRET, {
      expiresIn: "7d",
    })

    res.status(201).json({
      token,
      user: { id: newUser.id, email: newUser.email, name: newUser.name, role: newUser.role },
    })
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Login
router.post("/login", async (req, res) => {
  try {
    const { email, password } = req.body

    if (!email || !password) {
      return res.status(400).json({ error: "Email and password are required" })
    }

    const users = await readJSON("users.json")
    const user = users.find((u) => u.email === email)

    if (!user) {
      return res.status(400).json({ error: "Invalid credentials" })
    }

    const validPassword = await bcrypt.compare(password, user.password)
    if (!validPassword) {
      return res.status(400).json({ error: "Invalid credentials" })
    }

    const token = jwt.sign({ id: user.id, email: user.email, role: user.role }, JWT_SECRET, { expiresIn: "7d" })

    res.json({
      token,
      user: { id: user.id, email: user.email, name: user.name, role: user.role },
    })
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

export default router
