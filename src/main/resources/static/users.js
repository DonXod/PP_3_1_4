let tableBodyAdmin = document.querySelector('#tableBodyAdmin')

const urlUsers = 'http://localhost:8080/api/users'

let modalEdit = ''
let modalDelete = ''

function showUser(user, tableBody) {
    let tr = null
    let td = null
    tr = document.createElement('tr')
    td = document.createElement('td')
    td.textContent = user.id
    tr.appendChild(td)

    td = document.createElement('td')
    td.textContent = user.email
    tr.appendChild(td)

    td = document.createElement('td')
    td.textContent = user.age
    tr.appendChild(td)

    td = document.createElement('td')
    td.textContent = user.firstname
    tr.appendChild(td)

    td = document.createElement('td')
    td.textContent = user.lastname
    tr.appendChild(td)

    td = document.createElement('td')
    td.textContent = getRoleForUser(user)
    tr.appendChild(td)
    tableBody.appendChild(tr)
}

fetch(urlUsers)
    .then(res => res.json())
    .then(users => {
        users.forEach(user => {
            showUser(user, tableBodyAdmin)
        })
    })