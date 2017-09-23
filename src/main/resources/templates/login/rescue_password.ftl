<#import "/layout.ftl" as layout>
<#import "/spring.ftl" as spring/>

<@layout.mainLayout>
    <form action="/do_rescue_password" method="post" class="form-signin">
        <h2 class="form-signin-heading">Recuperar password</h2>
        <@spring.formInput "rescueForm.email" 'class="form-control" placeholder="Email address" autofocus="autofocus"'/>
        <#list spring.status.errorMessages as error>
            <b>${error}</b> <br>
        </#list>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Rescue</button>
        <a href="/login">Login</a>
    </form>
</@layout.mainLayout>
