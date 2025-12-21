# Bloom & Beauty - Flower Shop

A complete flower shop application with customer ordering and admin management.

## Features

### Customer Features
- Browse flower catalog
- Add flowers to cart
- User registration and authentication
- Place orders with delivery details
- Track order delivery status with fake delivery flow
- View order history

### Admin Features
- Add/delete flowers from catalog
- Manage all orders
- Update order status
- View customer information

## Project Structure

```
├── client/
│   └── js/
│       ├── api.js          # API helper functions
│       ├── auth.js         # Authentication logic
│       └── cart.js         # Cart management
├── backend/
│   ├── server.js           # Express server
│   ├── routes/             # API routes
│   ├── data/               # JSON data storage
│   └── middleware/         # Auth middleware
├── index.html              # Homepage
├── flowers.html            # Flower catalog
├── cart.html               # Shopping cart
├── checkout.html           # Checkout page
├── order-tracking.html     # Order tracking with delivery simulation
├── orders.html             # User order history
├── login.html              # Login page
├── register.html           # Registration page
├── admin.html              # Admin dashboard
├── about.html              # About page
├── contact.html            # Contact page
└── delivery.html           # Delivery information
```

## Getting Started

1. Start the backend server:
```bash
cd backend
npm install
npm start
```

2. Open `index.html` in your browser

3. Default admin credentials (create manually in backend/data/users.json):
   - Email: admin@bloom.com
   - Password: admin123
   - Role: admin

## API Endpoints

- POST `/api/auth/register` - Register new user
- POST `/api/auth/login` - Login user
- GET `/api/flowers` - Get all flowers
- POST `/api/flowers` - Add flower (admin only)
- DELETE `/api/flowers/:id` - Delete flower (admin only)
- POST `/api/orders` - Create order (authenticated)
- GET `/api/orders/my-orders` - Get user orders (authenticated)
- GET `/api/orders` - Get all orders (admin only)
- PATCH `/api/orders/:id/status` - Update order status (admin only)

## Technologies

- Frontend: HTML, CSS, Bootstrap 5, Vanilla JavaScript
- Backend: Node.js, Express
- Storage: JSON files
- Authentication: JWT, bcrypt
