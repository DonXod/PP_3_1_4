const tableBodyUser = document.querySelector('#tableBodyUser')

const urlUser = 'http://localhost:8080/api/users/auth'

fetch(urlUser)
    .then(res => res.json())
    .then(data => {
        console.log(data)
        let roles = getRoleForUser(data)
        let output = `
            <tr>
                <td> ${data.id} </td>
                <td> ${data.email} </td>
                <td> ${data.age} </td>
                <td> ${data.firstname} </td>
                <td> ${data.lastname} </td>
                <td> ${roles}</td>
            </tr>`
        tableBodyUser.innerHTML = output
    })


