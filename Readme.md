# FinTech Secure Ledger Engine

A high-performance, containerized cloud banking ledger built with **Spring Boot** and **PostgreSQL**, deployed natively to production cloud infrastructure. The core transactional engine features strict data integrity validation, automated schema migrations, structural zero-loss auditing tracking, and **Idempotency Validation Architecture** to prevent duplicate payment processing over distributed networks.

---

## Live Production Deployments
The backend architecture is fully operational and publicly reachable over the internet via secure cloud tunnels:

* **Production API Gate:** `https://fintech-production-ca89.up.railway.app`
* **Private Network Layer:** Connected securely to a cloud-managed PostgreSQL relational cluster container.

---

## System Architecture & Stack



* **Enterprise Framework:** Spring Boot 3.x (Java 21)
* **Data Access Layer:** Spring Data JPA / Hibernate ORM
* **Relational Database Engine:** Cloud-Managed PostgreSQL
* **Cloud Infrastructure Provider:** Railway (Automated Container Builds & Continuous Deployment)
* **API Testing Tooling:** Postman Enterprise Workspace

---

## Core Technical Features Applied

1. **Idempotency Network Security Gates:** Protects transactions against distributed network layer connection timeouts or double-click failure loops by verifying an exclusive string-token tracking cache before processing ledger mutations.
2. **Automated DDL Schema Mapping:** Hibernate maps structural configurations dynamically down to PostgreSQL schemas on engine startup, maintaining exact table relations (`users`, `wallets`, `transactions`).
3. **Robust Environment Variable Extraction:** Decouples operational secrets (database access ports, encrypted private passwords, public mapping bindings) completely from source code, fetching system variables live from cloud injectors.

---

## Real Distributed Testing Workflow

### 📋 Sample Input API Transaction Route
* **HTTP Method:** `POST`
* **Target Address URL:** `https://fintech-production-ca89.up.railway.app/api/wallets/transfer`
* **Payload Headers:** `Content-Type: application/json`

**Raw JSON Request Parameters Body:**
```json
{
  "sourceWalletId": "f2688e88-4f96-4fa4-8793-214379b9b317",
  "targetWalletId": "30ffe101-8d8f-4578-bef2-a0b3ae0c0705",
  "amount": 200.00,
  "idempotencyKey": "tx-cloud-test-001"
}
```


**Live Output API Transaction Response (200 OK)**
```json
{
  "amount": 200.0,
  "createdAt": "2026-05-26T11:26:49.689141167Z",
  "id": "eec72fea-a0d9-4f02-8833-88fd5fc752ec",
  "idempotencyKey": "tx-cloud-test-001",
  "sourceWallet": {
    "balance": 800.0,
    "currency": "USD",
    "id": "f2688e88-4f96-4fa4-8793-214379b9b317",
    "user": {
      "email": "alice@example.com",
      "id": "0a53d866-13f4-4740-9ecc-90fb431b4f45",
      "name": "Alice Smith"
    }
  },
  "status": "SUCCESS",
  "targetWallet": {
    "balance": 700.0,
    "currency": "USD",
    "id": "30ffe101-8d8f-4578-bef2-a0b3ae0c0705",
    "user": {
      "email": "bob@example.com",
      "id": "114f8b3f-755a-4cdb-aadc-8b3c2ac02ba4",
      "name": "Bob Jones"
    }
  }
}
```


**Step-by-Step Local Setup & Execution**
1. Clone & Access Project
```bash
git clone [https://github.com/arshavsuman20/FinTech.git](https://github.com/arshavsuman20/FinTech.git)
cd FinTech
```
2. Configure Local Properties Environment
Add your local database credentials into src/main/resources/application.properties:
```
Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/digital_wallet
spring.datasource.username=your_local_username
spring.datasource.password=your_local_password
spring.jpa.hibernate.ddl-auto=update
```
3. Compile & Start Application Server
```Bash
./mvnw clean spring-boot:run
```
The server will bind to local endpoint gateway: http://localhost:8080
