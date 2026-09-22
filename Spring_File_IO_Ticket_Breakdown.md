# Spring File I/O: TXT Deposits

**Goal:** Upload a TXT amount into the selected checking or savings account, then print its transactions.

Extract `Banking_File_IO_Starter.zip`, follow `README.md`, and keep your existing database. The frontend and printing are already complete. Sign in at `http://localhost:5173` and select an ACTIVE account.

## Ticket 1: Complete the TXT deposit

**User story:** As a customer, I want to upload a file containing `350` and add $350.00 to the account I selected.

**Edit one file:** `AccountFileController.java` in `backend/src/main/java/com/example/banking/controllers/`.

Inside `uploadDeposit(...)`, replace the TODO comment and 501 placeholder with three steps:

1. Read `file.getBytes()`, turn the bytes into a UTF-8 `String`, and call `.trim()` to remove surrounding spaces and line breaks.
2. Convert the text into a `BigDecimal` amount with `new BigDecimal(text)`.
3. Return `banking.deposit(id, auth.getName(), amount, "TXT deposit")`.

Keep the supplied file checks and `try/catch`. The existing deposit method updates the balance and records the transaction. You do not need to change a service, entity or database table.

### Test the upload

Use `samples/deposit.txt`. It contains only:

```text
350
```

| Action | Expected result |
| --- | --- |
| Select checking and upload once. | Checking increases by $350.00; savings stays unchanged. |
| Select savings and upload once. | Savings increases by $350.00; checking stays unchanged. |
| Refresh the page. | The balances and new TXT deposit transactions remain. |
| Upload the empty sample or `hello`. | An error appears; no deposit is created. |

Each successful upload creates another deposit. Upload once per test.

## Ticket 2: Verify printing - already supplied

1. Select an account and click **Print transactions**.
2. In print preview, confirm only that account's heading and history appear.
3. Choose a printer or **Save as PDF**. Repeat with your other account.

The button calls `window.print()`. Print CSS hides the rest of the page. No Java PDF code is needed. Printing uses the history currently displayed; Refresh first when needed.

**Explain:** What does `MultipartFile` contain? Why turn bytes into text? How does `id` select the account? Why reuse the existing deposit method?

**Submit:** Your completed controller, a screenshot of a successful TXT deposit, and a PDF saved from print preview.
