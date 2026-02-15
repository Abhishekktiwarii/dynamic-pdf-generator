
# ✅ **README.md — Dynamic PDF Generation API**

```markdown
# 📄 Dynamic PDF Generation API

A Spring Boot REST API that generates invoice-style PDFs dynamically from JSON input using a Java template engine. The generated PDF is stored locally and reused when the same input is provided again.

This application demonstrates dynamic document generation, caching strategy, and REST API design using Spring Boot.

---

## 🚀 Features

✅ Generate invoice PDF from request data  
✅ Store generated PDF in local storage  
✅ Return existing PDF if same data is provided (caching)  
✅ Download generated PDF via REST API  
✅ Thymeleaf template-based PDF layout  
✅ Testable using Postman / Swagger  
✅ Clean layered architecture  

---

## 🏗️ Tech Stack

- Java 17+
- Spring Boot 3
- Spring Web (REST API)
- Thymeleaf (Template Engine)
- OpenHTMLToPDF (HTML → PDF conversion)
- Lombok
- Maven

---

## 📂 Project Structure

```

src/main/java/com/pdfgenerator
│
├── controller        → REST endpoints
├── service           → Business logic
├── model             → Request DTOs
├── util              → PDF generation utility
│
src/main/resources
├── templates         → Thymeleaf HTML templates
├── application.properties

````

---

## ⚙️ Setup Instructions

### 1. Clone Project

```bash
git clone <repo-url>
cd dynamic-pdf
````

---

### 2. Install Dependencies

```bash
mvn clean install
```

---

### 3. Configure Application

Edit:

```
src/main/resources/application.properties
```

```properties
server.port=8081
spring.thymeleaf.cache=false
pdf.storage.path=./generated-pdfs
```

---

### 4. Run Application

```bash
mvn spring-boot:run
```

Server starts:

```
http://localhost:8081
```

---

## 📌 API Documentation

---

### ⭐ Generate Invoice PDF

### Endpoint

```
POST /api/pdf/generate
```

---

### Request Headers

```
Content-Type: application/json
```

---

### Request Body Schema

```json
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
      "rate": 0,
      "amount": 0
    }
  ]
}
```

---

### Sample Test Data (Use in Postman)

```json
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

### Response

```
200 OK
```

* Returns downloadable PDF file.
* Stored locally for reuse.

---

## 🧪 Testing with Postman

### Steps

1. Start application
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
7. PDF will download.

---

## 💾 Storage Behavior (Caching)

* Generated PDFs stored in:

```
generated-pdfs/
```

* System generates a unique hash based on request data.
* If same request is sent again:

  * Existing PDF returned
  * New PDF not generated

This improves performance and avoids duplicate processing.

---

## 🎯 How It Works (Flow)

```
Client Request
     ↓
REST Controller
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

## 📄 PDF Layout

PDF contains:

* Seller details
* Buyer details
* Item table
* Quantity, rate, amount

Generated using Thymeleaf HTML template.

---

## ⚠️ Assumptions

* No database required.
* Local file storage used.
* Single-page invoice format.
* Only REST APIs implemented (no UI).

---

## 🔮 Possible Improvements

* Swagger/OpenAPI documentation
* Request validation
* Global exception handling
* Unit tests (TDD)
* Docker support
* Cloud storage integration
* Database caching
* Authentication & authorization

---

## 👨‍💻 Author

Abhishek Tiwari

```

---
