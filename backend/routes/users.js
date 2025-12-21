import express from "express"
import db from "../db/sqlite.js"
import { authenticateAdmin } from "../middleware/auth.js"

const router = express.Router()

router.get("/", authenticateAdmin, (req, res) => {
  try {
    const users = db.prepare(`
      SELECT id, email, name, role, createdAt
      FROM users
      ORDER BY createdAt DESC
    `).all()

    res.json(users)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

export default router
