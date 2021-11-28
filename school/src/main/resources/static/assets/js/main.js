

$('document').ready(function() {
	var count = 0;
	$('.editClass').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(classe, status) {
			$('#classLabelEdit').val(classe.classLabel);
			$('#gradeEdit').val(classe.grade);
			$('#scholaticYearEdit').val(classe.scholaticYear);
			$('#idClasseEdit').val(classe.idClasse);
		});
		$('#update_class').modal();
	
	});
	
    $("#addStuddentsToClass").on('click',function(){
	    var data=$(this).data();
	     var select=document.createElement('select')
	     select.classList.add('form-control')
	     select.setAttribute('id','newstudent')
		$.get("/all" , function(students,status){
			 
				var option = document.createElement("option");
				option.value="";
				option.text="-- Select a student --"
				select.appendChild(option);
			for (i = 0; i < students.length; i++) {
				if(students[i].classe == null){
				var option = document.createElement("option");
				option.value = students[i].idPerson;
				option.text = students[i].firstName +" "+ students[i].lastName;
				select.appendChild(option);
			}
			}
		})
		document.getElementById('addStudent_classe').appendChild(select)
		var newStudent= document.getElementById('newstudent')
		newStudent.addEventListener("change",function(){
		     val=$(this).val();
		     if(val!=""){
					document.getElementById('confirmAddStudentToClasse').classList.remove('disabled');
					var href="/updateStudent-classe/"+val+"/"+data.classe+"/0"
					document.getElementById('confirmAddStudentToClasse').setAttribute('href',href)
				}else{
					document.getElementById('confirmAddStudentToClasse').classList.add('disabled')
				}
	    })
		
		
	
	
	
	
		var href="/updateStudent-classe/{idStudent}/{idClasse}/{idOldClasse}" 
	$('#add_student_toclasse').modal();
	})
	
	$('#add_student_toclasse').on('hidden.bs.modal', function (e) {
 		$('#addStudent_classe').html('');
	})

	$('.deleteClass').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#confirmSeleteClass').attr('href', href);
		$('#delete_class').modal();
	});

    $('.deleteSchoolTeach').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#confirmSelectedField').attr('href', href);
		$('#delete_schoolTeach').modal();
	});
	
	$('#transfer_student').on('hidden.bs.modal', function (e) {
 		$('#TransferStudentTab').html('');
	})
	
	
	  $('.transferStudent').on('click', function(event) {
		event.preventDefault();
		var data=$(this).data();
		var tab=document.getElementById('TransferStudentTab');
		var newRow = tab.insertRow();
		var FirstnameCell= newRow.insertCell();
		var LastnameCell=newRow.insertCell();
		var ClassesListCell=newRow.insertCell();
		var firstname=document.createTextNode(data.firstname)
		var lastname=document.createTextNode(data.lastname)
		FirstnameCell.appendChild(firstname)
		LastnameCell.appendChild(lastname)
		var select = document.createElement('select');
		select.className = "form-control";
		select.setAttribute('id','newClasse')
		$.get("/classes/getclasses" , function(classes,status){
			 
				var option = document.createElement("option");
				option.value="";
				option.text="-- Select a classe --"
				select.appendChild(option);
			for (i = 0; i < classes.length; i++) {
				if(classes[i].idClasse != data.classe){
				var option = document.createElement("option");
				option.value = classes[i].idClasse;
				option.text = classes[i].classLabel;
				select.appendChild(option);
			}
			}
				
			
		})
		ClassesListCell.appendChild(select)
		$('#transfer_student').modal();
	    var newClasse =document.getElementById('newClasse');
	    newClasse.addEventListener("change",function(){
		     val=$(this).val();
		     if(val!=""){
					document.getElementById('transferStudent').classList.remove('disabled');
					var href="/updateStudent-classe/"+data.idstudent+"/"+val+"/"+data.classe 
					document.getElementById('transferStudent').setAttribute('href',href)
				}else{
					document.getElementById('transferStudent').classList.add('disabled')
				}
	    })
	    
	   
	    
	    
		
	});

  





	$("#addTeachersAndSub").on('click', function(event) {

		count++;
		event.preventDefault();
		
		
        if(count==1){
		var href1 = $(this).attr('href');
		var href2 = "/subjects/all"
		var tbodyRef = document.getElementById("teachersAndSubTab");
		var newRow = tbodyRef.insertRow();
		var SelectTeacherCell = newRow.insertCell();
		var SelectSubCell = newRow.insertCell();
		var removeCell = newRow.insertCell();
		var i;
		var select1 = document.createElement('select');
		var select2 = document.createElement('select');
		select1.className = "form-control teachers"
		select1.setAttribute("name", "idPerson")
		select2.className = "form-control subjects"
		select2.setAttribute("name", "idSubject")

		//getting Teachers List
		$.get(href1, function(teachers, status) {


			for (i = 0; i < teachers.length; i++) {
				var option = document.createElement("option");
				option.value = teachers[i].idPerson;
				option.text = teachers[i].firstName;
				select1.appendChild(option);
			}
		})
		//getting subjects List
		$.get(href2, function(subjects, status) {
			for (i = 0; i < subjects.length; i++) {
				var option = document.createElement("option");
				option.value = subjects[i].idSubject;
				option.text = subjects[i].subjectLabel;
				select2.appendChild(option);
			}
		})
		SelectTeacherCell.appendChild(select1);
		SelectSubCell.appendChild(select2);

		
			var btn=document.createElement("button");
			btn.className="btn btn-success"
			btn.innerHTML="Save"
			//btn.setAttribute('onclick','func(event)')
			btn.setAttribute('type','submit')
			document.getElementById("savebtn").appendChild(btn);
		
      }

	});


	


});
