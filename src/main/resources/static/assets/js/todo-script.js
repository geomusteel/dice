const keyEventCheck = function () {
    let textarea = document.getElementById('todo-input');
    textarea.addEventListener('keydown', event => keyCodeCheck(event), {once: true});
};

const keyCodeCheck = function (event) {
    let key = event.key || event.keyCode;
    const todoInput = document.querySelector('#todo-input');
    if (todoInput.value !== '' && key === 'Enter' || key === 13) {
        createTodo(todoInput);
    }
};

const createTodo = function (todoInput) {
    const todoList = document.querySelector('#todo-list');
    const newLi = document.createElement('li');
    const newSpan = document.createElement('span');
    const newBtn = document.createElement('button')

    newBtn.addEventListener('click', () => {
        newLi.classList.toggle('complete');
    });

    newLi.addEventListener('dblclick', () => {
        newLi.remove();
    });

    newSpan.textContent = todoInput.value;
    newLi.appendChild(newBtn);
    newLi.appendChild(newSpan);
    todoList.appendChild(newLi);
    todoInput.value = '';
};

const deleteAll = function (){
    const liList = document.querySelectorAll('li');
}