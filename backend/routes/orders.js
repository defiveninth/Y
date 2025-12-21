import express from "express"
import db from "../db/sqlite.js"
import { authenticateToken, authenticateAdmin } from "../middleware/auth.js"

const router = express.Router()

// Create order
router.post("/", authenticateToken, (req, res) => {
  try {
    const { items, total, deliveryAddress, deliveryDate } = req.body

    if (!items || !total || !deliveryAddress || !deliveryDate) {
      return res.status(400).json({ error: "All fields are required" })
    }

    const newOrder = {
      id: Date.now().toString(),
      userId: req.user.id,
      items: JSON.stringify(items),
      total: Number.parseFloat(total),
      deliveryAddress,
      deliveryDate,
      status: "pending",
      createdAt: new Date().toISOString(),
    }

    db.prepare(`
      INSERT INTO orders (
        id, userId, items, total, deliveryAddress,
        deliveryDate, status, createdAt
      )
      VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    `).run(
      newOrder.id,
      newOrder.userId,
      newOrder.items,
      newOrder.total,
      newOrder.deliveryAddress,
      newOrder.deliveryDate,
      newOrder.status,
      newOrder.createdAt
    )

    res.status(201).json({
      ...newOrder,
      items: JSON.parse(newOrder.items),
    })
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Get logged-in user's orders
router.get("/my-orders", authenticateToken, (req, res) => {
  try {
    const orders = db.prepare(`
      SELECT * FROM orders WHERE userId = ?
      ORDER BY createdAt DESC
    `).all(req.user.id)

    const parsedOrders = orders.map(order => ({
      ...order,
      items: JSON.parse(order.items),
    }))

    res.json(parsedOrders)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Get all orders (admin only)
router.get("/", authenticateAdmin, (req, res) => {
  try {
    const orders = db.prepare(`
      SELECT * FROM orders ORDER BY createdAt DESC
    `).all()

    const parsedOrders = orders.map(order => ({
      ...order,
      items: JSON.parse(order.items),
    }))

    res.json(parsedOrders)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Update order status (admin only)
router.patch("/:id/status", authenticateAdmin, (req, res) => {
  try {
    const { status } = req.body

    if (!status) {
      return res.status(400).json({ error: "Status is required" })
    }

    const result = db.prepare(`
      UPDATE orders SET status = ? WHERE id = ?
    `).run(status, req.params.id)

    if (result.changes === 0) {
      return res.status(404).json({ error: "Order not found" })
    }

    const updatedOrder = db
      .prepare("SELECT * FROM orders WHERE id = ?")
      .get(req.params.id)

    updatedOrder.items = JSON.parse(updatedOrder.items)

    res.json(updatedOrder)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

export default router
