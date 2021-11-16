
$('document').ready(function(){
	$('.editClass').on('click',function(event){
		event.preventDefault();
		var href=$(this).attr('href');
		console.log(href);
		$.get(href, function(classe, status){
			$('#classLabelEdit').val(classe.classLabel);
			$('#gradeEdit').val(classe.grade);
			$('#scholaticYearEdit').val(classe.scholaticYear);
			$('#idClasseEdit').val(classe.idClasse);
		});
		$('#update_class').modal();
		
		
	});
	
	
	$('.deleteClass').on('click', function(event){
		event.preventDefault();
		var href=$(this).attr('href');
		$('#confirmSeleteClass').attr('href',href);
		$('#delete_class').modal();
	});
	
	
	
	
});
