// 退職者グレーアウト
const userListElements = document.querySelectorAll('.userList');
userListElements.forEach(userList => {
	const userStatus = userList.getAttribute('data-status');
	const buttons = userList.querySelectorAll('.buttons');

	if (userStatus !== '1') {
		userList.style.backgroundColor = '#E6E6E6';
		userList.style.pointerEvents = 'none';

		// ボタンも無効化
		buttons.forEach(button => {
			button.style.pointerEvents = 'none';
			button.style.opacity = '0.6';
		});
	}
});

document.addEventListener("DOMContentLoaded", function() {
	const filterForm = document.getElementById("filterForm");
	const allUsersRadio = document.getElementById("allUsersRadio");
	const activeUsersRadio = document.getElementById("activeUsersRadio");
	const retiredUsersRadio = document.getElementById("retiredUsersRadio");

	// ラジオボタンの変更イベントを監視
	allUsersRadio.addEventListener("change", function() {
		filterForm.submit(); // 全ユーザーを選択した場合、フォームをサブミット
	});

	activeUsersRadio.addEventListener("change", function() {
		filterForm.submit(); // 在籍中を選択した場合、フォームをサブミット
	});

	retiredUsersRadio.addEventListener("change", function() {
		filterForm.submit(); // 退職ユーザーを選択した場合、フォームをサブミット
	});
});

document.addEventListener("DOMContentLoaded", function() {
	const pageSizeSelect = document.getElementById("pageSize");
	const filterForm = document.getElementById("filterForm");

	pageSizeSelect.addEventListener("change", function() {
		filterForm.submit(); // セレクトボックスが変更されたときにフォームを送信
	});
});


