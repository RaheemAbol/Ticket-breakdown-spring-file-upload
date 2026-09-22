import { useState } from 'react';

export default function AccountFiles({ account, onUpload, busy }) {
  const [file, setFile] = useState(null);
  const frozen = account.status === 'FROZEN';

  async function submit(event) {
    event.preventDefault();
    if (!file || busy || frozen) return;
    const form = event.currentTarget;
    if (await onUpload(account.id, file)) {
      setFile(null);
      form.reset();
    }
  }

  return <div className="action-grid">
    <section className="panel">
      <h2>Deposit from a text file</h2>
      <p className="muted">Into {account.accountType.toLowerCase()} account #{account.id}.</p>
      <p className="muted">Choose a .txt file containing one amount, such as 350. Maximum 1 KB.</p>
      {frozen && <p className="inline-warning">This account is frozen. Deposits are unavailable.</p>}
      <form onSubmit={submit}>
        <label>Deposit file
          <input type="file" accept=".txt,text/plain" required
            disabled={busy || frozen}
            onChange={event => setFile(event.target.files?.[0] ?? null)} />
        </label>
        <button disabled={busy || frozen || !file}>Upload deposit</button>
      </form>
      <p className="muted">Each successful upload creates a new deposit.</p>
    </section>
    <section className="panel">
      <h2>Print transactions</h2>
      <p className="muted">Print this account's history, or choose Save as PDF in the print dialog.</p>
      <button type="button" className="secondary" disabled={busy}
        onClick={() => window.print()}>Print transactions</button>
    </section>
  </div>;
}
