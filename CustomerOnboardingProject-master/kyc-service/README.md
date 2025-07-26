
# 🧾 KYC Microservice

This microservice is responsible for managing Know Your Customer (KYC) document submission and verification as part of the customer onboarding workflow.

---

## 🔧 Tech Stack

- **Java 21**
- **Spring Boot 3.3.1**
- **Spring Data JPA**
- **Spring Validation**
- **Oracle DB**
- **OpenFeign** (for customer data validation)
- **Swagger (Springdoc OpenAPI)**
- **Eureka Client**

---

## 📦 Features

### ✅ KYC Document Submission (Section 2.2)
- Upload PAN, Aadhaar, and Photograph (all mandatory)
- Photograph is accepted as base64 string and stored as BLOB
- Metadata (`documentType`, `documentUrl`) is extracted and stored
- Validates PAN and Aadhaar against customer-service records (via Feign)
- Returns proper success and error responses
- Stores KYC with default status as `PENDING`

### ✅ KYC Verification by Admin (Section 2.3)
- Admin can mark a KYC as `VERIFIED` or `REJECTED`
- Rejected KYCs can include an admin remark
- (Optional) Email sending via Kafka is disabled by default
- API to check current KYC status & remark is available

---

## 📂 Endpoints

### 🔽 Base URL
```
http://localhost:8082/kycservice/api
```

### 📝 Submit KYC (POST)
```
POST /kyc
```
**Request Body:**
```json
{
  "customerId": "CUST123",
  "panNumber": "ABCDE1234F",
  "aadhaarNumber": "123456789012",
  "photograph": "/9j/4AAQSkZJRgABAQEASABIAAD..."
}
```

---

### 🔄 Review KYC (Admin)
```
PUT /kyc/{customerId}/review
```
**Request Body:**
```json
{
  "kycStatus": "REJECTED",
  "adminRemark": "Invalid PAN document"
}
```

---

### 📌 Get KYC by ID
```
GET /kyc/{customerId}
```

---

### 📄 Get All KYC Entries
```
GET /kyc
```

---

### 📥 Get KYC Status
```
GET /kyc/{customerId}/status
```
**Response:**
```json
{
  "kycStatus": "REJECTED",
  "adminRemark": "Invalid document"
}
```

---

### 🖼️ View Photograph
```
GET /kyc/{customerId}/photo/view
```
Returns image as `image/jpeg`.

---

## ✅ Validation

| Field | Validation |
|-------|------------|
| `customerId` | Not blank |
| `panNumber` | Exactly 10 characters |
| `aadhaarNumber` | Exactly 12 digits |
| `photograph` | Base64 string, required |

---

## 🗂️ Database Table: `kyc_details`

| Column         | Type     | Description                 |
|----------------|----------|-----------------------------|
| customer_id    | VARCHAR  | Primary Key                 |
| pan_number     | VARCHAR  | PAN                         |
| aadhaar_number | VARCHAR  | Aadhaar                     |
| photograph     | BLOB     | Stored as decoded base64    |
| kyc_status     | VARCHAR  | PENDING / VERIFIED / REJECTED |
| admin_remark   | VARCHAR  | Admin review notes          |
| document_type  | VARCHAR  | e.g., image/jpeg            |
| document_url   | VARCHAR  | Simulated source string     |

---

## 🧪 Swagger UI

Visit:
```
http://localhost:8082/swagger-ui.html
```

---

## ⚙️ Eureka

This microservice is registered as:
```
spring.application.name=kyc-service
```

---

## 📬 Kafka (Disabled)

Kafka producer logic for sending emails is included but commented out in:
- `EmailProducer.java`
- `KycServiceImpl.java`

To enable:
- Run Kafka and email microservice
- Uncomment logic inside `reviewKyc(...)`

---

## ✅ Status

| Requirement                      | Status |
|----------------------------------|--------|
| Submit KYC                       | ✅ Done |
| Validate PAN/Aadhaar             | ✅ Done |
| Store base64 photo as BLOB       | ✅ Done |
| Store metadata                   | ✅ Done |
| Admin Review                     | ✅ Done |
| KYC Status API                   | ✅ Done |
| Email on REJECT (via Kafka)      | ⚠️ Disabled |

---

## 🧑‍💻 Developed By

Ajay Sharma  
Oracle Financial Services Software  
Microservices Project — Code Contest Submission
