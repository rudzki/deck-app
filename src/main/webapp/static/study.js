function showAnswerText() {
	$("#answerText").css("display","block");
	$("button#answerButton").css("display", "none");
	$("#cardScoring").css("display","block");
}

$("button#answerButton").on("click", showAnswerText);
