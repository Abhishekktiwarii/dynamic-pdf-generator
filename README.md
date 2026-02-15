# 📄 Dynamic PDF Generation API (Spring Boot)

A **Spring Boot REST API** that dynamically generates **invoice-style PDF documents** from JSON input using a Java template engine.
The generated PDF is stored locally and automatically reused when the same request data is provided again.

This project demonstrates **dynamic document generation, REST API design, template rendering, and caching strategy**.

---

# 🚀 Overview

This application exposes a REST API that:

* Accepts invoice data via JSON request
* Generates a formatted invoice PDF using a template engine
* Stores the generated PDF in local storage
* Returns an existing PDF when identical data is provided again
* Provides downloadable PDF response

The system improves performance by avoiding duplicate PDF generation through request-based caching.

---

# ✨ Features

✅ Generate invoice PDF dynamically from request data
✅ Store generated PDF in local storage
✅ Reuse existing PDF for identical requests (caching mechanism)
✅ Download generated PDF via REST API
✅ Clean layered architecture (Controller → Service → Utility)
✅ Thymeleaf template-based invoice layout
✅ Testable using Postman or Swagger

---

# 🏗️ Tech Stack

* **Java 17+**
* **Spring Boot 3**
* **Spring Web (REST API)**
* **Thymeleaf (Template Engine)**
* **OpenHTMLToPDF (HTML → PDF conversion)**
* **Lombok**
* **Maven**

---

# 📂 Project Structure

```
dynamic-pdf
│
├── src/main/java/com/pdfgenerator
│   ├── controller        # REST API endpoints
│   ├── service           # Business logic
│   ├── model             # Request DTOs
│   ├── util              # PDF generation utility
│   └── DynamicPdfApplication.java
│
├── src/main/resources
│   ├── templates         # Thymeleaf HTML template
│   └── application.properties
│
└── generated-pdfs        # Stored generated PDFs
```

---

# ⚙️ Setup & Installation

## 1️⃣ Clone Repository

```
git clone <repository-url>
cd dynamic-pdf
```

---

## 2️⃣ Install Dependencies

```
mvn clean install
```

---

## 3️⃣ Configure Application

Open:

```
src/main/resources/application.properties
```

Add or verify:

```
server.port=8081
spring.thymeleaf.cache=false
pdf.storage.path=./generated-pdfs
```

---

## 4️⃣ Run Application

```
mvn spring-boot:run
```

Application runs at:

```
http://localhost:8081
```

---

# 📌 API Documentation

## ⭐ Generate Invoice PDF

### Endpoint

```
POST /api/pdf/generate
```

---

## Request Headers

```
Content-Type: application/json
```

---

## Request Body Schema

```
{
  "seller": "string",
  "sellerGstin": "string",
  "sellerAddress": "string",
  "buyer": "string",
  "buyerGstin": "string",
  "buyerAddress": "string",
  "items": [
    {
      "name": "string",
      "quantity": "string",
      "rate": number,
      "amount": number
    }
  ]
}
```

---

## Sample Request (Testing Data)

Use this in Postman:

```
{
  "seller": "XYZ Pvt. Ltd.",
  "sellerGstin": "29AABBCCDD121ZD",
  "sellerAddress": "New Delhi, India",
  "buyer": "Vedant Computers",
  "buyerGstin": "29AABBCCDD131ZD",
  "buyerAddress": "New Delhi, India",
  "items": [
    {
      "name": "Product 1",
      "quantity": "12 Nos",
      "rate": 123.0,
      "amount": 1476.0
    },
    {
      "name": "Product 2",
      "quantity": "5 Nos",
      "rate": 200.0,
      "amount": 1000.0
    }
  ]
}
```

---

## Response

```
200 OK
```

* Returns downloadable PDF file
* PDF stored locally
* Same request returns cached PDF

---

# 🧪 Testing Using Postman

### Steps

1. Start the application
2. Open Postman
3. Create POST request:

```
http://localhost:8081/api/pdf/generate
```

4. Add header:

```
Content-Type: application/json
```

5. Paste sample JSON
6. Click **Send**
7. PDF will download automatically

---

# 💾 Storage & Caching Strategy

* Generated PDFs stored in:

```
generated-pdfs/
```

* System generates a unique hash from request data.
* If the same request is sent again:

  * Existing PDF is returned
  * New PDF is not generated

This improves performance and prevents duplicate processing.

---

# ⚙️ Application Flow

```
Client Request
      ↓
REST Controller
      ↓
Service Layer
      ↓
Thymeleaf Template Rendering
      ↓
HTML → PDF Conversion
      ↓
Local Storage Save
      ↓
Return PDF Response
```

---

# 📄 Generated PDF Contents

The generated PDF includes:

* Seller information
* Buyer information
* Item table
* Quantity, rate, and amount details
* Invoice-style layout

The layout is defined using a Thymeleaf HTML template.

---

# ⚠️ Assumptions

* No database is used.
* Local file storage is used for caching.
* Single-page invoice format.
* Only REST APIs implemented (no UI).

---

# 🔮 Possible Improvements

* Swagger/OpenAPI documentation
* Request validation
* Global exception handling
* Unit tests (TDD)
* Docker deployment
* Cloud storage integration
* Database-based caching
* Authentication and authorization

---

# 👨‍💻 Author

**Abhishek Tiwari**
