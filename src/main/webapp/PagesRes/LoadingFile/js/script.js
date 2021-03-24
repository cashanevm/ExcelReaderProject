let input = document.querySelectorAll(".inputfile");
Array.prototype.forEach.call(input, function (input) {
	let label = input.nextElementSibling;
	let labelVal = label.innerHTML;

	input.addEventListener('change', function (e) {
		let fileName = '';
		if (this.files && this.files.length > 1) {
			fileName = (this.getAttribute('data-multiple-caption') || '').replace('{count}', this.files.length);
		}
		else {
			fileName = e.target.value.split('\\').pop();
		}

		if (fileName) {
			label.querySelector('span').innerHTML = fileName;
		}
		else {
			label.innerHTML = labelVal;
		}
	});
});