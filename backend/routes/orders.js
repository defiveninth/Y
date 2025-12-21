import express from "express"
import { readJSON, writeJSON } from "../utils/db.js"
import { authenticateToken, authenticateAdmin } from "../middleware/auth.js"

const router = express.Router()

// Create order
router.post("/", authenticateToken, async (req, res) => {
  try {
    const { items, total, deliveryAddress, deliveryDate } = req.body

    if (!items || !total || !deliveryAddress || !deliveryDate) {
      return res.status(400).json({ error: "All fields are required" })
    }

    const orders = await readJSON("orders.json")
    const newOrder = {
      id: Date.now().toString(),
      userId: req.user.id,
      items,
      total: Number.parseFloat(total),
      deliveryAddress,
      deliveryDate,
      status: "pending",
      createdAt: new Date().toISOString(),
    }

    orders.push(newOrder)
    await writeJSON("orders.json", orders)

    res.status(201).json(newOrder)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Get user's orders
router.get("/my-orders", authenticateToken, async (req, res) => {
  try {
    const orders = await readJSON("orders.json")
    const userOrders = orders.filter((o) => o.userId === req.user.id)
    res.json(userOrders)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Get all orders (admin only)
router.get("/", authenticateAdmin, async (req, res) => {
  try {
    const orders = await readJSON("orders.json")
    res.json(orders)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Update order status (admin only)
router.patch("/:id/status", authenticateAdmin, async (req, res) => {
  try {
    const { status } = req.body
    const orders = await readJSON("orders.json")
    const orderIndex = orders.findIndex((o) => o.id === req.params.id)

    if (orderIndex === -1) {
      return res.status(404).json({ error: "Order not found" })
    }

    orders[orderIndex].status = status
    await writeJSON("orders.json", orders)

    res.json(orders[orderIndex])
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

export default router
