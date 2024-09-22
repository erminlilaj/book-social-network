import {Component, OnInit} from '@angular/core';
import {PageResponseBookResponse} from "../../../../services/models/page-response-book-response";
import {BookService} from "../../../../services/services/book.service";
import {Router} from "@angular/router";
import {BookResponse} from "../../../../services/models/book-response";

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrl: './my-books.component.scss'
})
export class MyBooksComponent implements OnInit{
  bookResponse: PageResponseBookResponse= {};
  page =0;
  size=3;
  // isLastPage: any;



  constructor(
    private bookService: BookService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllBooks();
  }

  private findAllBooks() {
    this.bookService.findAllBooksByOwner({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (books)=>{
        this.bookResponse=books;
      }
    });
  }

  goToFirstPage() {
    this.page=0;
    this.findAllBooks();
  }

  goToPreviousPage() {
    this.page--;
    this.findAllBooks();
  }

  goToNextPage() {
    this.page++;
    this.findAllBooks();
  }

  goToLastPage() {
    this.page=this.bookResponse.totalPages as number-1;
    this.findAllBooks();
  }

  goToPage(page: number) {
    this.page=page;
    this.findAllBooks();
  }
  get isLastPage(): boolean {
    return this.page===this.bookResponse.totalPages as number-1;
  }


  archiveBook(book: BookResponse) {

  }

  shareBook(book: BookResponse) {

  }

  editBook(book: BookResponse) {
this.router.navigate(['books','manage',book.id]);
  }
}


