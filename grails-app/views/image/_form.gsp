<%@ page import="eshop.Image" %>



<div class="fieldcontain ${hasErrors(bean: imageInstance, field: 'photo', 'error')} required">
	<label for="photo">
		<g:message code="image.photo.label" default="Photo" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="photo" name="photo" />
</div>

<div class="fieldcontain ${hasErrors(bean: imageInstance, field: 'goodId', 'error')} ">
	<label for="goodId">
		<g:message code="image.goodId.label" default="Good Id" />
		
	</label>
	<g:textField name="goodId" value="${imageInstance?.goodId}"/>
</div>

