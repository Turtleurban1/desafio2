const baseUrl = "/api/books";
const resultText = document.getElementById("result-books");

async function loadBooks() {
    const res = await fetch(baseUrl);
    const books = await res.json();
    displayBooks(books);
    resultText.innerText = "Lista de todos los libros";
}

function displayBooks(books) {
    const list = document.getElementById("bookList");
    list.innerHTML = "";
    books.forEach(book => {
        const li = document.createElement("li");
        li.className = "bg-gray-50 p-3 rounded shadow flex justify-between items-center";
        li.innerHTML = `
            <span><strong>${book.title}</strong> por ${book.author} (${book.publicationYear})</span>
            <button onclick="deleteBook(${book.id})" class="text-red-600 hover:text-red-800">
                <i class="fas fa-trash-alt"></i>
            </button>
        `;
        list.appendChild(li);
    });
}

document.getElementById("bookForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const title = document.getElementById("title").value.trim();
    const author = document.getElementById("author").value.trim();
    const yearInput = document.getElementById("year").value;

    // Validaciones
    if (!title) {
        Swal.fire({
            icon: 'warning',
            title: 'Campo vacío',
            text: 'Por favor, ingresa el título del libro.'
        });
        return;
    }

    if (!author) {
        Swal.fire({
            icon: 'warning',
            title: 'Campo vacío',
            text: 'Por favor, ingresa el autor del libro.'
        });
        return;
    }

    const year = Number(yearInput);

    if (!yearInput || isNaN(year) || !Number.isInteger(year) || year < 0) {
        Swal.fire({
            icon: 'warning',
            title: 'Año inválido',
            text: 'El año debe ser un número entero positivo.'
        });
        return;
    }

    await fetch(baseUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, author, publicationYear: year })
    });

    e.target.reset();

    Swal.fire({
        icon: 'success',
        title: 'Libro agregado',
        text: `${title} fue agregado exitosamente.`,
        timer: 3000,
        showConfirmButton: false
    });

    loadBooks();
});

async function searchBooks() {
    const keyword = document.getElementById("search").value;
    const res = await fetch(`${baseUrl}/search?title=${encodeURIComponent(keyword)}`);
    const books = await res.json();
    displayBooks(books);
    resultText.innerText = "Resultados de: " + keyword;
}

async function deleteBook(id) {
    const result = await Swal.fire({
        title: '¿Eliminar este libro?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar',
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6'
    });

    if (result.isConfirmed) {
        await fetch(`${baseUrl}/${id}`, { method: "DELETE" });

        Swal.fire({
            icon: 'success',
            title: 'Libro eliminado',
            timer: 3000,
            showConfirmButton: false
        });

        loadBooks();
    }
}

loadBooks();
