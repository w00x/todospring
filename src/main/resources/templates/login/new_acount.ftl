<#import "/layout.ftl" as layout>
<#import "/spring.ftl" as spring/>

<@layout.mainLayout>
    <#if error?? >
        Contraseña no son iguales
    </#if>

    <#if logout?? >
        You have been logged out.
    </#if>

    <form action="/create_acount" method="post" class="form-signin">
        <#if error_noequal?? && error_noequal == 1 >
            <p>Email o password incorrectos</p>
        </#if>

        <h2 class="form-signin-heading">Please sign up</h2>
        <@spring.formInput "accountForm.email" 'class="form-control" placeholder="Email address" autofocus="autofocus"'/>
        <#list spring.status.errorMessages as errorr>
            <b>${errorr}</b> <br>
        </#list>
        <@spring.formInput "accountForm.password" 'class="form-control" placeholder="Password" autofocus="autofocus"', 'password'/>
        <#list spring.status.errorMessages as errorr>
            <b>${errorr}</b> <br>
        </#list>
        <@spring.formInput "accountForm.rePassword" 'class="form-control" placeholder="Reingresar password" autofocus="autofocus"', 'password'/>
        <#list spring.status.errorMessages as errorr>
            <b>${errorr}</b> <br>
        </#list>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
        <a href="/login">Login</a><br/>
        <a href="/rescue_password">Recuperar password</a>
    </form>
</@layout.mainLayout>