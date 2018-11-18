<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<form action="publishCard" method="POST" class="form-inline">
	<div class="form-group">
		<label for="question">Question</label> <input type="text"
			name="question" id="question" class="form-control" />
	</div>
	<div class="form-group">
		<label for="answer">Answer</label>
		<textarea name="answer" id="answer" class="form-control" rows="10"
			cols="80"></textarea>
	</div>
	<div class="form-group">
		<label for="category">Category</label> <select name="categoryId"
			id="categoryId" class="form-control">
			
			<c:forEach items="${categories}" var="category">
			<option value="${category.key}">${category.value}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label>&nbsp;</label> <input type="submit" name="submit"
			value="Add Card" class="btn btn-default" />
	</div>
</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />