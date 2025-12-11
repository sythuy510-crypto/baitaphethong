# Order System with RabbitMQ (Java + VS Code)

## 1. Overview
This project demonstrates an asynchronous order processing system using:
- Java 17+
- Spring Boot
- RabbitMQ
- VS Code
- H2 In‑Memory Database

When a user creates an order, the API responds immediately while heavy tasks are processed in the background:
- Sending confirmation email (simulated via logs)
- Updating inventory
- Writing detailed order logs
- Updating processing status

RabbitMQ ensures reliable message delivery with acknowledgements and durable queues.

---

## 2. System Architecture

### Components:
- **Order API** — Receives order requests and stores them.
- **RabbitMQ** — Message broker to guarantee delivery.
- **Background Worker** — Processes tasks asynchronously.
- **H2 Database** — Stores orders, status, inventory, and logs.

### Workflow:
1. User creates an order via API.
2. Spring Boot saves it to DB and immediately returns a success response.
3. API publishes `OrderCreated` message to RabbitMQ.
4. Worker receives message and performs:
   - Fake email sending
   - Inventory update
   - Logging
   - Status update

---

## 3. Requirements

### Software:
- Java JDK 17 or 21
- Visual Studio Code
- Extensions:
  - Extension Pack for Java
  - Spring Boot Tools
  - Maven for Java
  - Lombok
- RabbitMQ Server (Docker recommended)

---

## 4. Start RabbitMQ

### Option A — Docker (recommended)
```
docker run -d --rm --name rabbitmq   -p 5672:5672 -p 15672:15672   rabbitmq:3-management
```

RabbitMQ UI: http://localhost:15672  
Username: `guest`  
Password: `guest`

### Option B — Windows Installer
Install Erlang → Install RabbitMQ → Start RabbitMQ service.

---

## 5. Run the Project on Visual Studio Code

### Step 1 — Open Project
- Open VS Code → File → Open Folder → choose the project folder

### Step 2 — Install Maven Dependencies
VS Code will detect `pom.xml` → click **"Load Maven Project"**.

### Step 3 — Run Application
- Open file `OrderBgRabbitmqApplication.java`
- Click **Run** (or press F5)

Spring Boot server starts at:
```
http://localhost:8080
```

---

## 6. API Usage

### Create Order
```
POST /api/orders
Content-Type: application/json

{
  "customerName": "Nguyen Van A",
  "productId": "P001",
  "quantity": 2,
  "totalPrice": 199.0
}
```

Response:
```
Order received. Check your email for confirmation. (orderId=1)
```

### Check Order Status
```
GET /api/orders/1/status
```

### View Recent Orders
```
GET /api/orders/recent
```

---

## 7. Why RabbitMQ?
| Feature | Redis | RabbitMQ |
|--------|--------|----------|
| Message durability | Medium | **High** |
| Acknowledgements | Limited | **Yes** |
| No‑loss guarantee | No | **Strong** |
| Routing / Exchange | No | **Yes** |
| Best for background jobs | Medium | **Excellent** |

RabbitMQ is chosen because this project requires:
- No lost messages  
- Acknowledgement  
- Durable queues  
- Worker retry behavior  

---

## 8. Project Structure
```
src/
 ├── controller/
 ├── service/
 ├── listener/
 ├── repository/
 ├── model/
 ├── config/
 ├── OrderBgRabbitmqApplication.java
resources/
 ├── application.properties
pom.xml
README.md
```

---

## 9. Author
- Student: [Your Name]
- Course: Technology 4.0 / Distributed Systems
- Instructor: [Teacher Name]
