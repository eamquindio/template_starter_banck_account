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
| 3    |                 |             |              |               |                  |

---

## 💻 Objetivo 3 — Implementación en Java

### 📋 Instrucciones para completar la tarea

Una vez que hayas completado los objetivos 1 y 2 en papel, es momento de implementar la solución en código Java.

#### 🔧 Pasos a seguir:

1. **Abre el proyecto en tu IDE favorito** (IntelliJ IDEA, Eclipse, VS Code, etc.)

2. **Localiza la clase `Cuenta.java`** en el paquete `edu.eam.logica`
   - Esta clase ya tiene la estructura básica definida
   - Tu tarea es completar los métodos que están vacíos

3. **Implementa los siguientes métodos:**
   - `depositar(double monto)`: 
     - Debe validar que el monto sea mayor a 0
     - Sumar el monto al saldo
     - Incrementar el contador de transacciones
     - Aplicar comisión si corresponde (más de 3 transacciones)
     - Retornar `true` si la operación fue exitosa, `false` si no
   
   - `retirar(double monto)`:
     - Debe validar que el monto sea mayor a 0
     - Verificar que haya saldo suficiente (incluyendo posible comisión)
     - Restar el monto del saldo
     - Incrementar el contador de transacciones
     - Aplicar comisión si corresponde
     - Retornar `true` si la operación fue exitosa, `false` si no
   
   - `calcularComision()`:
     - Si las transacciones son 3 o menos, retornar 0.0
     - Si son más de 3:
       - Para cuenta "Básica": retornar 1.50
       - Para cuenta "Premium": retornar 0.75

4. **Ejecuta los tests** para verificar tu implementación:
   ```bash
   mvn test
   ```
   o desde tu IDE, ejecuta la clase `CuentaTest.java`

5. **Asegúrate de que todos los tests pasen** antes de hacer commit

#### 📝 Notas importantes:

- **NO modifiques** los tests existentes en `CuentaTest.java`
- **NO cambies** las firmas de los métodos (nombre, parámetros, tipo de retorno)
- **NO agregues** nuevos atributos a la clase `Cuenta`
- Todos los valores monetarios deben manejarse con precisión de 2 decimales
- Recuerda que el saldo nunca puede quedar negativo
- Las comisiones se aplican inmediatamente cuando se exceden las 3 transacciones gratuitas

#### 🎯 Criterios de evaluación:

Tu implementación será evaluada automáticamente mediante GitHub Classroom. Los tests verificarán:

1. ✅ Manejo correcto del saldo en depósitos y retiros
2. ✅ Aplicación correcta de comisiones según el tipo de cuenta
3. ✅ Validación de operaciones inválidas (montos negativos, saldo insuficiente)
4. ✅ Contador correcto de transacciones mensuales
5. ✅ Comportamiento correcto en casos límite

#### 🚀 Entrega:

1. Completa la implementación de los métodos en `Cuenta.java`
2. Verifica que todos los tests pasen localmente
3. Haz commit y push de tus cambios:
   ```bash
   git add .
   git commit -m "Implementación completa de la clase Cuenta"
   git push origin main
   ```
4. GitHub Classroom ejecutará automáticamente los tests y te dará tu calificación

¡Buena suerte! 🍀
