import express from "express"
import { readJSON, writeJSON } from "../utils/db.js"
import { authenticateAdmin } from "../middleware/auth.js"

const router = express.Router()

// Get all flowers
router.get("/", async (req, res) => {
  try {
    const flowers = await readJSON("flowers.json")
    res.json(flowers)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Add flower (admin only)
router.post("/", authenticateAdmin, async (req, res) => {
  try {
    const { name, price, description, image, category } = req.body

    if (!name || !price) {
      return res.status(400).json({ error: "Name and price are required" })
    }

    const flowers = await readJSON("flowers.json")
    const newFlower = {
      id: Date.now().toString(),
      name,
      price: Number.parseFloat(price),
      description: description || "",
      image: image || "",
      category: category || "other",
      createdAt: new Date().toISOString(),
    }

    flowers.push(newFlower)
    await writeJSON("flowers.json", flowers)

    res.status(201).json(newFlower)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Update flower (admin only)
router.put("/:id", authenticateAdmin, async (req, res) => {
  try {
    const { name, price, description, image, category } = req.body

    if (!name || !price) {
      return res.status(400).json({ error: "Name and price are required" })
    }

    const flowers = await readJSON("flowers.json")
    const flowerIndex = flowers.findIndex((f) => f.id == req.params.id)


    if (flowerIndex === -1) {
      return res.status(404).json({ error: "Flower not found" })
    }

    const updatedFlower = {
      ...flowers[flowerIndex],
      name,
      price: Number.parseFloat(price),
      description: description || "",
      image: image || "",
      category: category || "other",
      updatedAt: new Date().toISOString(),
    }

    flowers[flowerIndex] = updatedFlower
    await writeJSON("flowers.json", flowers)

    res.json(updatedFlower)
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

// Delete flower (admin only)
router.delete("/:id", authenticateAdmin, async (req, res) => {
  try {
    const flowers = await readJSON("flowers.json")
    const filteredFlowers = flowers.filter((f) => f.id !== req.params.id)

    if (flowers.length === filteredFlowers.length) {
      return res.status(404).json({ error: "Flower not found" })
    }

    await writeJSON("flowers.json", filteredFlowers)
    res.json({ message: "Flower deleted" })
  } catch (error) {
    res.status(500).json({ error: "Server error" })
  }
})

export default router
