
let fileElements = document.querySelectorAll("[type=file]");

let imageArea = document.querySelectorAll(".image_area");

fileElements.forEach(item => item.addEventListener('change', preview));

function preview(){
	
	let index = Array.from(fileElements).indexOf(this);
	//세개의 경로에서  파일이 어디서 첨부되었는지 알아야함
	if(this.files&&this.files[0]){
		let reader = new FileReader();
		reader.readAsDataURL(this.files[0]);
		reader.onload = function(){
			imageArea[index].innerHTML = '<img src="' + reader.result + '">';
		}
	}
}