<?php
include("controller/c_demo.php");
$c_Demo = new c_demo();
$type_expenseID = $_POST['type_expenseID'];
$type_expense = $_POST['type_expenseName'];
$description = $_POST['description'];

$content = $c_Demo->insertTypeExpense($type_expenseID,$type_expenseName,$description);

if($content)
	echo "success";
else
	echo "error";

?>