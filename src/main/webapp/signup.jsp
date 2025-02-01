<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up - Kumhara</title>
    <link rel="stylesheet" href="resources/css/styles.css">
    <link rel="stylesheet" href="resources/css/auth-styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
    <nav class="navbar">
        <a href="home" class="nav-brand">Kumhara</a>
    </nav>

    <div class="auth-container">
        <div class="auth-box">
            <h1>Create Account</h1>
            <p class="auth-subtitle">Join us to explore delicious food</p>

            <% if (request.getAttribute("error") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <form action="signup" method="post" class="auth-form">
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" required 
                           placeholder="Enter your full name">
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required 
                           placeholder="Enter your email">
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" required 
                           placeholder="Enter your phone number">
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required 
                           placeholder="Create a password">
                </div>

                <div class="form-group">
                    <label for="confirm-password">Confirm Password</label>
                    <input type="password" id="confirm-password" name="confirmPassword" required 
                           placeholder="Confirm your password">
                </div>

                <div class="form-footer terms">
                    <label class="remember-me">
                        <input type="checkbox" name="terms" required> 
                        I agree to the <a href="#">Terms &amp; Conditions</a>
                    </label>
                </div>

                <button type="submit" class="auth-button">Create Account</button>
            </form>

            <div class="auth-links">
                Already have an account? <a href="login">Login</a>
            </div>
        </div>
    </div>
</body>
</html> 