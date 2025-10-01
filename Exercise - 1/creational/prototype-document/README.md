# Creational • Prototype — Document Templates

**Pattern goal:** Clone existing object graphs and modify shallowly/deeply as needed.

**Run**
```bash
javac Main.java
java Main
# choose: PrototypeDocumentDemo
```

**User inputs**
- Choose a base template (Resume or Report)
- Enter custom title and author

**Classes**
- `Document` (Prototype)
- `Resume`, `Report` (Concrete prototypes)
- `PrototypeDocumentDemo` (Demo harness)
