<#import "/layout.ftl" as layout>
<@layout.mainLayout>
    <#if param.message[0] >
        <h1>${param.message[0]}</h1>
    </#if>

    <a href="/new_account">${label.form.loginSignUp}</a>
    </form>
</@layout.mainLayout>