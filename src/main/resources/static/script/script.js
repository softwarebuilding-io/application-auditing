document.addEventListener("DOMContentLoaded", function () {
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(function (alert) {
        setTimeout(function () {
            alert.style.display = 'none';
        }, 5000); // Hide after 5 seconds
    });
});

(function () {
    'use strict';
    window.addEventListener('load', function () {
        const forms = document.getElementsByClassName('needs-validation');
        Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

document.querySelectorAll('.copy-code-button').forEach(button => {
    button.addEventListener('click', function() {
        // Find the <pre><code> element that is a sibling of the button
        const codeBlock = this.nextElementSibling;
        const code = codeBlock.innerText;

        // Copy code to clipboard
        navigator.clipboard.writeText(code).then(() => {
            // Optionally, provide feedback to the user that the text was copied
            button.textContent = 'Copied!';
            setTimeout(() => button.textContent = 'Copy Code', 2000);
        }).catch(err => {
            console.error('Failed to copy: ', err);
        });
    });
});