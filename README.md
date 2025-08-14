# 🏦 Ejercicio 1 – Mini-Simulador de Cuenta de Ahorro (con tipos de cuenta)

---

## 🎯 Objetivos del ejercicio
1. **Identificar los datos** que necesita el sistema para representar una cuenta de ahorro, definiendo su nombre, tipo y descripción.
2. **Comprender la lógica de cálculo** para determinar el saldo final en diferentes escenarios, aplicando las reglas de negocio.

---

## 📜 Contexto de negocio
El **Banco Aurora** está desarrollando su nueva **Cuenta de Ahorro Digital**.  
Cada cuenta:
- **Siempre tiene un saldo** que refleja el dinero disponible.
- Registra cada operación como **depósito** o **retiro**.
- Lleva el conteo de transacciones mensuales.
- Aplica comisiones por exceso dependiendo del **tipo de cuenta**.

El banco ofrece dos tipos de cuenta:
- **Básica** → Comisión por exceso: $1.50 por transacción adicional.
- **Premium** → Comisión por exceso: $0.75 por transacción adicional.

El saldo **nunca puede ser negativo** y todos los valores se manejan con **dos decimales**.

---

## 📏 Reglas de negocio
- La cuenta inicia con un saldo (puede ser cero).
- Cada depósito **suma** al saldo.
- Cada retiro **resta** al saldo, pero no puede dejarlo negativo.
- Cada depósito o retiro cuenta como **una transacción** mensual.
- El cliente tiene un **máximo de 3 transacciones gratuitas**.
- A partir de la cuarta transacción, se cobra la **comisión según el tipo de cuenta** y se descuenta de forma inmediata.

---

## 🧩 Objetivo 1 — Identificación de datos

### 📌 Instrucciones
- Enumera los datos que el sistema necesita para representar la cuenta y sus operaciones, considerando que el tipo de cuenta influye en la comisión.
- Para cada dato, define:
    1. **Nombre propuesto**
    2. **Tipo de dato**
    3. **Descripción**

| # | Nombre propuesto | Tipo de dato | Descripción |
|---|------------------|--------------|-------------|
| 1 |                  |              |             |
| 2 |                  |              |             |
| 3 |                  |              |             |
| 4 |                  |              |             |
| 5 |                  |              |             |
| 6 |                  |              |             |
| 7 |                  |              |             |
| 8 |                  |              |             |

---

### 💡 Tips para reconocer los atributos
1. **Pregúntate qué debo recordar** entre una operación y otra.
   > Si un valor se usa más de una vez o se actualiza constantemente, probablemente es un atributo.
2. **Busca cantidades que cambian con las operaciones.**
   > Montos de dinero, conteos de operaciones, límites, etc.
3. **Piensa en reglas del negocio que dependan de un valor fijo.**
   > Ejemplo: la comisión por exceso depende del tipo de cuenta.
4. **Identifica datos que ayuden a tomar decisiones.**
   > Como “¿esta operación genera comisión?” o “¿se puede hacer este retiro?”.
5. **No olvides los datos que permiten clasificar o diferenciar clientes o cuentas.**
   > En este caso, el tipo de cuenta cambia la lógica de la comisión.
6. **Mantén el equilibrio entre lo general y lo específico.**
   > Evita datos innecesarios, pero incluye los esenciales para que las reglas funcionen.

---

## 🧮 Objetivo 2 — Situaciones para resolver en papel

**Consigna:**  
Para cada caso, calcula el saldo después de cada operación. Si se supera el límite de 3 transacciones, aplica inmediatamente la comisión correspondiente al **tipo de cuenta**.

---

**Situación 1**  
Tipo de cuenta: **Básica**  
Saldo inicial: $0.00  
Operaciones:
1. Depósito de $100.00
2. Retiro de $20.00
3. Depósito de $50.00

| Paso | Operación       | Saldo antes | Cambio (+/−) | Saldo después | Nº Transacciones |
|-----:|-----------------|-------------|--------------|---------------|------------------|
| 0    | Saldo inicial   |             |              |               |                  |
| 1    |                 |             |              |               |                  |
| 2    |                 |             |              |               |                  |
| 3    |                 |             |              |               |                  |

---

**Situación 2**  
Tipo de cuenta: **Básica**  
Saldo inicial: $0.00  
Operaciones:
1. Depósito de $200.00
2. Retiro de $50.00
3. Depósito de $20.00
4. Retiro de $30.00

| Paso | Operación       | Saldo antes | Cambio (+/−) | Saldo después | Nº Transacciones |
|-----:|-----------------|-------------|--------------|---------------|------------------|
| 0    | Saldo inicial   |             |              |               |                  |
| 1    |                 |             |              |               |                  |
| 2    |                 |             |              |               |                  |
| 3    |                 |             |              |               |                  |
| 4    |                 |             |              |               |                  |

---

**Situación 3**  
Tipo de cuenta: **Premium**  
Saldo inicial: $1000.00  
Operaciones:
1. Depósito de $500.00

| Paso | Operación       | Saldo antes | Cambio (+/−) | Saldo después | Nº Transacciones |
|-----:|-----------------|-------------|--------------|---------------|------------------|
| 0    | Saldo inicial   |             |              |               |                  |
| 1    |                 |             |              |               |                  |

---

**Situación 4**  
Tipo de cuenta: **Premium**  
Saldo inicial: $50.00  
Operaciones:
1. Retiro de $10.00
2. Depósito de $5.00
3. Retiro de $10.00
4. Retiro de $5.00
5. Depósito de $20.00

| Paso | Operación       | Saldo antes | Cambio (+/−) | Saldo después | Nº Transacciones |
|-----:|-----------------|-------------|--------------|---------------|------------------|
| 0    | Saldo inicial   |             |              |               |                  |
| 1    |                 |             |              |               |                  |
| 2    |                 |             |              |               |                  |
| 3    |                 |             |              |               |                  |
| 4    |                 |             |              |               |                  |
| 5    |                 |             |              |               |                  |

---

**Situación 5**  
Tipo de cuenta: **Básica**  
Saldo inicial: $30.00  
Operaciones:
1. Retiro de $50.00 (si no se permite, no modifica el saldo ni el contador)
2. Depósito de $20.00
3. Retiro de $10.00

| Paso | Operación       | Saldo antes | Cambio (+/−) | Saldo después | Nº Transacciones |
|-----:|-----------------|-------------|--------------|---------------|------------------|
| 0    | Saldo inicial   |             |              |               |                  |
| 1    |                 |             |              |               |                  |
| 2    |                 |             |              |               |                  |
| 3    |                 |             |              |               |                  |# simple_bank_account
# template_starter_banck_account
