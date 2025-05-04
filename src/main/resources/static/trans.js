const depositForm = document.getElementById('depositForm');
const withdrawForm = document.getElementById('withdrawForm');
const popup = document.getElementById('popup');
const popupMessage = document.getElementById('popup-message');

console.log("hello");

async function handleFormSubmit(e, url, form, successMessage) {
  e.preventDefault();
  const formData = new FormData(form);

  try {
    const response = await fetch(url, {
      method: 'POST',
      body: formData
    });

    if (response.ok) {
      form.reset();
      showPopup(successMessage);
    } else {
      const error = await response.text();
      alert(error);
    }
  } catch (err) {
    console.error(err);
    alert('An error occurred. Please try again.');
  }
}

function showPopup(message) {
  popupMessage.textContent = message;
  popup.classList.add('show');
  setTimeout(() => popup.classList.remove('show'), 5000);
}

function closePopup() {
  popup.classList.remove('show');
}

// Attach handlers
depositForm.addEventListener('submit', (e) => {
  handleFormSubmit(e, 'http://localhost:8091/deposit', depositForm, 'Deposit successful!');
});

withdrawForm.addEventListener('submit', (e) => {
  handleFormSubmit(e, 'http://localhost:8091/withdraw', withdrawForm, 'Withdrawal successful!');
});
