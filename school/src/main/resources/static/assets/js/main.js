
function getCheckedBoxes(chkboxName) {
  var checkboxes = document.getElementsByName(chkboxName);
  var checkboxesChecked = [];
  // loop over them all
  for (var i=0; i<checkboxes.length; i++) {
     // And stick the checked ones onto an array...
     if (checkboxes[i].checked) {
        checkboxesChecked.push(checkboxes[i].value);
     }
  }
  // Return the array if it is non-empty, or null
  return checkboxesChecked.length > 0 ? checkboxesChecked : null;
}
function getRemarks(){
	var texareas=document.getElementsByName("remarque");
	var remarques =[];
	  // loop over them all
  for (var i=0; i<texareas.length; i++) {
     // And stick the not empty ones onto an array...
     if (texareas[i].value !='') {
        remarques.push(texareas[i]);
     }
  }
    return remarques.length > 0 ? remarques : null;

}

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
$(".editbtn").on('click',function(event){
	event.preventDefault();
	
		var href1 = "/teachers/all";
		var href2 = "/subjects/all"
		
		var i;
		var select1 = document.createElement('select');
		var select2 = document.createElement('select');
		select1.className = "form-control teachers"
		select1.setAttribute("name", "idPerson")
		select2.className = "form-control subjects"
		select2.setAttribute("name", "idSubject")
		var select3=document.createElement('select');
		select3.className = "form-control salles"
		select3.setAttribute("name", "idSalle")
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
		//getting all Clasrooms
		$.get("/classroom/all", function(salles, status) {
			for (i = 0; i < salles.length; i++) {
				var option = document.createElement("option");
				option.value = salles[i].idSalle;
				option.text = salles[i].salleLabel;
				select3.appendChild(option);
			}
		})
		var data=$(this).data();
		var day=document.createElement("h1");
		day.appendChild(document.createTextNode(data.day));
		var hour= document.createElement("h1");
		
		hour.appendChild(document.createTextNode(data.starthour+' AM -  '+ parseInt(data.starthour +2)+' AM'));
		document.getElementById('day').appendChild(day);
		document.getElementById('startHour').appendChild(hour);
		document.getElementById('addTeachertoTimetab').appendChild(select1);
		document.getElementById('addSubjecttoTimetab').appendChild(select2);
		document.getElementById('addSalletoTimetab').appendChild(select3);
	    document.getElementById('dayinput').value=data.day;
	    document.getElementById('starthourinput').value=data.starthour;
	$('#add_timetableFields').modal();
});
$('#add_timetableFields').on('hidden.bs.modal', function (e) {
 		$('#day').html('');
 		$('#startHour').html('');
 		$('#addTeachertoTimetab').html('');
 		$('#addSubjecttoTimetab').html('');
 		$('#addSalletoTimetab').html('');
	})
	
$('.deleteField').on('click',function(event){
	event.preventDefault();
	var href= $(this).attr('href');
	$('#delteField').attr('href',href);
	
	
	$('#deleteFiledModal').modal();
});
$('.line').on('click',function(){
	var data=$(this).data();
     window.location = '/absences/'+data.id+'/'+data.idfield
})
/****** */


$('#AbsenceRegister').on('click',function(){
	var absentStudents= getCheckedBoxes("isAbsent");
	var remarques = getRemarks();
	const myMap = new Map();
	if(remarques!=null){
		for(var i=0;i<remarques.length;i++){
		myMap.set(remarques[i].dataset.idstudent,remarques[i].value);
	}
	}
	$.ajax({
		  type: 'GET', 
		  url: serviceEndpoint,
		  dataType: 'json',
		  contentType: 'json',
		  headers: { 'api-key':'myKey' },
		  success: onSuccess,
		  error: onFailure
		});
			
});







});

