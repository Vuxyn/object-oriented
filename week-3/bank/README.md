# Bank — Cara Compile & Run

Struktur:

```
bank/
  src/  -> Account.java, Customer.java, Bank.java, Main.java
  bin/  -> hasil .class
```

## Compile

Dari dalam folder `bank/`:

```powershell
javac -d bin src/*.java
```

## Run

Dari dalam folder `bank/`:

```powershell
java -cp bin Main
```
