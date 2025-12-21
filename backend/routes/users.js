import express from "express"
import { readJSON } from "../utils/db.js"
import { authenticateAdmin } from "../middleware/auth.js"

const router = express.Router()

// Get all users (admin only)
router.get("/", authenticateAdmin, async (req, res) => {
  try {
    const users = await readJSON("users.json")
    const usersWithoutPasswords = users.map(({ password, ...user }) => user)
    res.json(usersWithoutPasswords)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

export default router
