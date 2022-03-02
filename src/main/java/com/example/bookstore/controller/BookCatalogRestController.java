package com.example.bookstore.controller;

import com.example.bookstore.dto.BookRequest;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.exception.ErrorMessage;
import com.example.bookstore.exception.RestExceptionBase;
import com.example.bookstore.service.BookCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;

@RestController
@RequestScope
@RequestMapping("/books")
@CrossOrigin
@Validated
public class BookCatalogRestController {

	private BookCatalogService bookCatalogService;

	public BookCatalogRestController(BookCatalogService bookCatalogService) {
		this.bookCatalogService = bookCatalogService;
	}

	@GetMapping("{isbn}")
	public BookResponse findByIsbn(@PathVariable String isbn) {
		return bookCatalogService.findBookByIsbn(isbn);
	}

	@DeleteMapping("{isbn}")
	public BookResponse deleteByIsbn(@PathVariable String isbn) {
		return bookCatalogService.deleteBook(isbn);
	}

	@GetMapping
	public Collection<BookResponse> findAllBooks(
			@RequestParam(required = false, defaultValue = "0") @Min(0) int pageNo,
			@RequestParam(required = false, defaultValue = "10") @Max(25) int pageSize) {
		return bookCatalogService.findAll(pageNo, pageSize);
	}

	@PostMapping
	public BookResponse addBook(@RequestBody BookRequest book) {
		return bookCatalogService.addBook(book);
	}

	@PutMapping("{isbn}")
	public BookResponse updateBook(@PathVariable String isbn, @RequestBody BookRequest book) {
		return bookCatalogService.updateBook(book);
	}

	@ExceptionHandler({ RestExceptionBase.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleErrors(RestExceptionBase e) {
		return new ErrorMessage(e.getMessageId(), e.getDebugId(), e.getMessage());
	}
}
