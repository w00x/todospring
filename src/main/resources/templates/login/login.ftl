<#import "/layout.ftl" as layout>
<#import "/spring.ftl" as spring/>

<@layout.mainLayout>
    <#if error??>
        Invalid username or password.
    </#if>

    <#if logout??>
        You have been logged out.
    </#if>

    <form action="/logincheck" method="post" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" name="username" id="username" class="form-control" placeholder="Email address" required="required" autofocus="autofocus"></input>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="password" class="form-control" placeholder="Password" required="required"></input>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <a href="/new_account">Crear cuenta</a><br/>
        <a href="/rescue_password">Recuperar password</a>
    </form>
</@layout.mainLayout>
