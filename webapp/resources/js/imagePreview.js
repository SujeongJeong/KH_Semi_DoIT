/* input type file 요소들*/
let fileElements = document.querySelectorAll("[type=file]");
/* div image_area 요소들*/
let imageArea = document.querySelectorAll(".textarea");
/* change 이벤트가 발생하는 상황 (file 태그에 첨부가 발생한 상황)에 preview 함수 동작*/
fileElements.forEach(item => item.addEventListener('change', preview));

function preview(){
//	여기서 this는 input type = file 중에 파일이 첨부된 element이다
//  event가 발생한 file중에 몇번째 file 인지를 알 기위해  index를 사용해준다.
	let index = Array.from(fileElements).indexOf(this);
	
	if(this.files && this.files[0]){
		let reader = new FileReader();
		// 데이터 url형식으로 이미지 src 속성에 담아서 표현
		reader.readAsDataURL(this.files[0]);
		// on.load = 잘 읽어졌을 때
		reader.onload = function(){
//  fileReader.result = 
// 파일의 내용을 반환한다. 이 속성은 읽기가 완료 된 후에만 사용 가능 하며 데이터의 형식은 읽기 작업에 어떤 함수가 사용되었는가에 의해 정해진다.	
			imageArea[index].innerHTML = '<img src="' + reader.result + '">';
		}
	}
}