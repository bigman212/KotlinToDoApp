## About mobile "ToDo reminder"
- OC : Android.
- Language : Kotlin.
- Current version: 1.1 (stable).
- Technologies used : PultusORM, Kotlin extensions.

## Суть проекта 
Реализация работы с базой данных через ORM. В качестве ORM была найдена и использована PultusORM. 
Список выводится на ListView с помощью ArrayAdapter.

## Как работает
При нажатии на " + " в правой нижней части экрана (FloatingPushButton), откроется диалог в котором можно ввести задачу для добавления в заметки. После этого эта заметка будет добавлена в список и выведена на экран. При нажатии на галочку (справа от заметки), заметка удаляется. 
