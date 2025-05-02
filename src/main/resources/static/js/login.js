document.addEventListener("DOMContentLoaded", function () {
    const usernameInput = document.getElementById("username");
    const message = document.getElementById("username-availability");

    usernameInput.addEventListener("input", function () {
        const username = usernameInput.value.trim();
        if (username.length < 3) {
            message.textContent = "";
            return;
        }

        fetch(`/check-username?username=${encodeURIComponent(username)}`)
            .then(response => response.json())
            .then(isAvailable => {
                if (isAvailable) {
                    message.textContent = "✅ Username is available";
                    message.style.color = "green";
                } else {
                    message.textContent = "❌ Username is taken";
                    message.style.color = "red";
                }
            })
            .catch(err => console.error("Error checking username", err));
    });
});
