import express from "express"
import cors from "cors"
import authRoutes from "./routes/auth.js"
import flowersRoutes from "./routes/flowers.js"
import ordersRoutes from "./routes/orders.js"
import usersRoutes from "./routes/users.js"

const app = express()
const PORT = 3000

// Middleware
app.use(cors())
app.use(express.json())

app.use((req, res, next) => {
  console.log(`${req.method} ${req.url}`)
  next()
})

app.get("/api/health", (req, res) => {
  res.json({ status: "ok", message: "Server is running" })
})

// Routes
app.use("/api/auth", authRoutes)
app.use("/api/flowers", flowersRoutes)
app.use("/api/orders", ordersRoutes)
app.use("/api/users", usersRoutes)

app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`)
  console.log(`API endpoints available at http://localhost:${PORT}/api`)
})
