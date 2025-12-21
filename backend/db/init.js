import db from "./sqlite.js"

db.prepare(`
CREATE TABLE IF NOT EXISTS users (
  id TEXT PRIMARY KEY,
  email TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  name TEXT NOT NULL,
  role TEXT DEFAULT 'customer',
  createdAt TEXT
)
`).run()

db.prepare(`
CREATE TABLE IF NOT EXISTS flowers (
  id TEXT PRIMARY KEY,
  name TEXT NOT NULL,
  price REAL NOT NULL,
  description TEXT,
  image TEXT,
  category TEXT,
  createdAt TEXT,
  updatedAt TEXT
)
`).run()

db.prepare(`
CREATE TABLE IF NOT EXISTS orders (
  id TEXT PRIMARY KEY,
  userId TEXT,
  items TEXT,
  total REAL,
  deliveryAddress TEXT,
  deliveryDate TEXT,
  status TEXT,
  createdAt TEXT,
  FOREIGN KEY (userId) REFERENCES users(id)
)
`).run()

console.log("SQLite tables initialized")
