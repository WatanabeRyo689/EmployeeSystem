const lists = document.getElementsByClassName('userList');
for (i = 0; i < lists.length; i++) {
    let userStatus = document.getElementsByClassName('enabled');
    let hensyu = document.getElementsByName('hensyu');
    let taisyoku = document.getElementsByName('taisyoku');
    let sakujyo = document.getElementsByName('sakujyo');
    if (userStatus[i].value != 1) {
        lists[i].style.backgroundColor = '#E6E6E6';
        lists[i].style.pointerEvents = 'none';
        // テーブルソート機能でホバー箇所に色がつくため無効化
        hensyu[i].style.pointerEvents = 'none';
        taisyoku[i].style.pointerEvents = 'none';
        sakujyo[i].style.pointerEvents = 'none';

    }
}