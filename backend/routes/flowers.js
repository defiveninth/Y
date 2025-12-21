import express from "express"
import db from "../db/sqlite.js"
import { authenticateAdmin } from "../middleware/auth.js"

const router = express.Router()

router.get("/", (req, res) => {
  const flowers = db.prepare("SELECT * FROM flowers").all()
  res.json(flowers)
})

router.post("/", authenticateAdmin, (req, res) => {
  const { name, price, description, image, category } = req.body
  if (!name || !price) {
    return res.status(400).json({ error: "Name and price are required" })
  }

  const flower = {
    id: Date.now().toString(),
    name,
    price: Number(price),
    description: description || "",
    image: image || "",
    category: category || "other",
    createdAt: new Date().toISOString(),
  }

  db.prepare(`
    INSERT INTO flowers VALUES (?, ?, ?, ?, ?, ?, ?, NULL)
  `).run(
    flower.id,
    flower.name,
    flower.price,
    flower.description,
    flower.image,
    flower.category,
    flower.createdAt
  )

  res.status(201).json(flower)
})

router.put("/:id", authenticateAdmin, (req, res) => {
  const { name, price, description, image, category } = req.body

  const result = db.prepare(`
    UPDATE flowers SET
      name = ?, price = ?, description = ?, image = ?, category = ?, updatedAt = ?
    WHERE id = ?
  `).run(
    name,
    Number(price),
    description || "",
    image || "",
    category || "other",
    new Date().toISOString(),
    req.params.id
  )

  if (result.changes === 0) {
    return res.status(404).json({ error: "Flower not found" })
  }

  res.json({ message: "Flower updated" })
})

router.delete("/:id", authenticateAdmin, (req, res) => {
  const result = db
    .prepare("DELETE FROM flowers WHERE id = ?")
    .run(req.params.id)

  if (result.changes === 0) {
    return res.status(404).json({ error: "Flower not found" })
  }

  res.json({ message: "Flower deleted" })
})

export default router
