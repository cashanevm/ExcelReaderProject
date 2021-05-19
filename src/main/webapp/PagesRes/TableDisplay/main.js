'use strict';

function bemClass(block, elem, modifier, extra) {
	let result = `${block}`;
	if (elem) result += `__${elem}`;
	if (modifier) result += `--${modifier}`;
	if (extra) result += extra;
	return result;
}

function bemSelector(...names) {
	return `.${bemClass(...names)}`;
}

const state = [];
const forms = document.querySelectorAll(bemSelector('form'));

forms.forEach(function (form) {
	const input = form.querySelector(bemSelector('form', 'input'));
	const btnContainer = form.querySelector(bemSelector('form', 'btn-container'));
	const buttons = form.querySelectorAll(bemSelector('form', 'btn'));
	const [resetBtn, submitBtn] = buttons; // destructuring

	const formState = {
		initialValue: input.value,
		btnVisible: false,
	};

	state.push(formState);

	input.addEventListener('input', function (event) {
		const newValue = event.target.value;

		if (formState.initialValue !== newValue) {
			if (!formState.btnVisible) {
				showElement(btnContainer);
			}
			formState.btnVisible = true;
		} else {
			formState.btnVisible = false;
			hideElement(btnContainer);
		}
	});

	buttons.forEach(btn => {
		btn.addEventListener('click', function () {
			formState.btnVisible = false;
			hideElement(btnContainer);
		});
	});

	submitBtn.addEventListener('click', function (event) {
                    //event.preventDefault();
		formState.initialValue = input.value;
		// request (fetchAPI / asynchronous)
		console.log('submitting...');
	});

	resetBtn.addEventListener('click', function () {
		input.value = formState.initialValue;
	});
});

function hideElement(element) {
	element.className = element.className
		.split(' ')
		.filter(cn => !cn.includes('visible'))
		.join(' ');
}

// only for 1 block class :)
function showElement(element) {
	element.classList.add(`${element.className}--visible`);
}

const submitAllBtn = document.querySelector(bemSelector('submit-all'));
const resetAllBtn = document.querySelector(bemSelector('reset-all'));
const submitBtns = document.querySelectorAll(bemSelector('form', 'btn', null, '[type=submit]'));
const resetBtns = document.querySelectorAll(bemSelector('form', 'btn', null, '[type=button]'));

console.log(submitBtns, resetBtns);

//submitAllBtn.addEventListener('click', () => {
//	submitBtns.forEach(btn => {
//		btn.click();
//	});
//})
//
//resetAllBtn.addEventListener('click', () => {
//	resetBtns.forEach(btn => {
//		btn.click();
//	});
//})