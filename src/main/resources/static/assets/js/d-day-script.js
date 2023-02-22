const messageContainer = document.querySelector('#d-day-message');
const container = document.querySelector('#d-day-container');

/**
 * 로컬스토리지에 저장된<br>
 * 시간정보<br>
 * 사이트 접속시 실행되며<br>
 * 값이 비어있을 수 있다.
 */
const savedDate = localStorage.getItem('saved-date');

/**
 * 생성된 인터벌을<br>
 * 저장하는 배열
 */
const intervalIdArr = [];

container.style.display = 'none';
messageContainer.innerHTML = '<div class="text-white display-6">D-Day를 입력해 주세요.</div>';

/**
 * 사용자가 카운트다운 시작을 클릭시<br>
 * 현재 인풋태그에서 작성된 데이터를 가져와<br>
 * 년, 월, 일에 데이터를 리턴해주는 메서드
 * @return 예) YYYY-MM-DD
 */
const dateFormMaker = function () {
    const inputYear = document.querySelector("#target-year-input").value;
    const inputMonth = document.querySelector("#target-month-input").value;
    const inputDate = document.querySelector("#target-date-input").value;

    return `${inputYear}-${inputMonth}-${inputDate}`;
};

/**
 * 매개변수로 받은 시간을 기준으로 <br>
 * 로컬스토리지 데이터 유무 여부를 판별<br>
 * remaining 데이터를 생성해서 반환
 */
const remainingMake = function (data) {
    // 로컬스토리지에 저장할 데이터를 판별하는 로직
    // 매개변수로 받은 시간과 로컬스토리지에 받은 시간이 같은지 체크 후
    // 같지 않다면 로컬스토리지에 매개변수로 받은 시간을 입력해준다.
    if (data !== savedDate) {
        localStorage.setItem('saved-date', data);
    }

    // nowDate = 현재시간
    const nowDate = new Date();
    // targetDate = 사용자가 입력한 시간
    const targetDate = new Date(data).setHours(0, 0, 0, 0);
    // remaining = 카운트에 표기될 남은 시간
    return (targetDate - nowDate) / 1000;
}

/**
 * 매개변수로 시간을 받고<br>
 * remaining 변수를 가공해서<br>
 * html 태그에 입력
 */
const counterMake = function (data) {

    // remaining 생성 메서드
    const remaining = remainingMake(data);

    // if
    // 남은 시간이 0보다 작다면 실행 될 로직
    // 타이머가 종료되었다는 문구가 표기되며
    // 인터벌을 지워준다.
    // if else
    // 입력 된 데이터가 숫자가 아닐경우 실행 될 로직
    // 유효한 시간대가 아닙니다. 라는 문구가 표기되며
    // 인터벌을 지워준다.
    if (remaining <= 0) {
        container.style.display = 'none';
        messageContainer.innerHTML = '<div class="text-white display-6">타이머가 종료되었습니다.</div>';
        messageContainer.style.display = 'block';
        setClearInterval();
        return;
    } else if (isNaN(remaining)) {
        container.style.display = 'none';
        messageContainer.innerHTML = '<div class="text-white display-6">유효한 시간대가 아닙니다.</div>';
        messageContainer.style.display = 'block';
        setClearInterval();
        return;
    }

    // remaining 변수를 이용해
    // 실제 사용가능한 시간으로 변경하는 오브젝트
    const remainingObj = {
        remainingDate: Math.floor(remaining / 3600 / 24),
        remainingHours: Math.floor((remaining / 3600) % 24),
        remainingMin: Math.floor((remaining / 60) % 60),
        remainingSec: Math.floor(remaining % 60)
    }

    // 반복문 사용을 위한 배열값 세팅
    const timeKeys = Object.keys(remainingObj);
    const documentArr = ['days', 'hours', 'min', 'sec'];

    /**
     * 매개변수로 받은 시간이 10보다 작다면<br>
     * 숫자를 붙여 양식에 맞게 <br>
     * 출력되게 해주는 메서드<br>
     * 출력 예) 9 -> 09 로변경
     */
    const format = function (time) {
        if (time < 10) {
            return '0' + time;
        } else {
            return time;
        }
    }

    // html id를 통해 태그를 찾고
    // 찾은 태그에 텍스트를 입력해주는 반복문
    for (let i = 0; i < documentArr.length; i++) {
        document.getElementById(documentArr[i]).textContent = format(remainingObj[timeKeys[i]]);
    }

};

/**
 * 사용자가 카운트 시작하며<br>
 * 인터벌을 사용해<br>
 * 1초마다 화면에 남은 시간을<br>
 * 뿌려주는 메서드
 */
const starter = function (targetDateInput) {

    if (!targetDateInput) {
        targetDateInput = dateFormMaker();
    }
    container.style.display = 'block';
    messageContainer.style.display = 'none';
    setClearInterval();
    counterMake(targetDateInput);
    let intervalId = setInterval(() => counterMake(targetDateInput), 1000);
    intervalIdArr.push(intervalId);
};

/**
 * 인터벌을 초기화 해주는 메서드
 */
const setClearInterval = function () {
    for (let i = 0; i < intervalIdArr.length; i++) {
        clearInterval(intervalIdArr[i]);
    }
};

/**
 * 카운트다운의 시간을<br>
 * 초기화 하는 메서드
 */
const resetTimer = function () {
    container.style.display = 'none';
    messageContainer.innerHTML = '<div class="text-white display-6">D-Day를 입력해 주세요.</div>';
    messageContainer.style.display = 'block';
    localStorage.removeItem('saved-date');
    setClearInterval();
};

// 로컬저장소에 데이터가 있다면
// starter 메서드를 실행해주고
// 그게 아니라면
// d-day 입력하라는 문구를 출력
if (savedDate !== null) {
    starter(savedDate);
} else {
    container.style.display = 'none';
    messageContainer.innerHTML = '<div class="text-white display-6">D-Day를 입력해 주세요.</div>';
}