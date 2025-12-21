# Flower Shop Backend

## Setup

1. Install dependencies:
```
cd backend
npm install
```

2. Start the server:
```
npm run dev
```

## Default Admin Account

- Email: admin@flowers.com
- Password: admin123

## API Endpoints

### Authentication
- POST /api/auth/register - Register new user
- POST /api/auth/login - Login user

### Flowers
- GET /api/flowers - Get all flowers
- POST /api/flowers - Add flower (admin only)
- DELETE /api/flowers/:id - Delete flower (admin only)

### Orders
- POST /api/orders - Create order (authenticated)
- GET /api/orders/my-orders - Get user's orders (authenticated)
- GET /api/orders - Get all orders (admin only)
- PATCH /api/orders/:id/status - Update order status (admin only)

### Users
- GET /api/users - Get all users (admin only)
