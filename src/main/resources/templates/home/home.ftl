<#import "/layout.ftl" as layout>
<#import "/spring.ftl" as spring/>

<@layout.mainLayout>
	<form action="/todo/save" method="post">
		<div class="form-group">
			<@spring.formInput "todoForm.description" 'class="form-control" autofocus="autofocus"'/>
			<#list spring.status.errorMessages as error>
                <b>${error}</b> <br>
			</#list>
		</div>
	</form>

	<table class="table">
		<thead>
		<tr>
			<th>Estado</th>
			<th>Descripción</th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<#list todos as todo>
			<tr id="tr_$todo.id">
				<td><input type="checkbox" name="active" <#if todo.done>checked</#if> class="check" data-id="${todo.id}" id="checkid_${todo.id}" /></td>
				<td>
					<span id="show_${todo.id}">${todo.description}</span>
					<input id="edit_in_${todo.id}" value="${todo.description}" type="text" class="edit" data-id="${todo.id}" style="display: none;">
				</td>
				<td>
					<span id="save_${todo.id}" data-id="${todo.id}" style="cursor: pointer; display: none;" class="glyphicon glyphicon-floppy-disk save" aria-hidden="true"></span>
					<span id="edit_${todo.id}" data-id="${todo.id}" style="cursor: pointer;" class="glyphicon glyphicon-pencil edit" aria-hidden="true"></span>
					<span id="delete_${todo.id}" data-id="${todo.id}" style="cursor: pointer;" class="glyphicon glyphicon-trash delete" aria-hidden="true"></span>
				</td>
			</tr>
		</#list>
		</tbody>
	</table>
	<script src="/js/bootstrap.min.js"></script>
</@layout.mainLayout>