import {Component, EventEmitter, Input, Output} from '@angular/core';
import {BookResponse} from "../../../../services/models/book-response";

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrl: './book-card.component.scss'
})
export class BookCardComponent {
  private _book: BookResponse={};
  private _manageBook = false;
  private _bookCover: string | undefined;

  get book(): BookResponse {
    return this._book;
  }

@Input()
  set book(value: BookResponse) {
    this._book = value;
  }



  get manageBook(): boolean {
    return this._manageBook;
  }
@Input()
  set manageBook(value: boolean) {
    this._manageBook = value;
  }



  get bookCover(): string | undefined {
    if(this._book.cover){
      return 'data:image/jpeg;base64,' + this._book.cover;//to convert the base 64 to image
    }
    return 'https://picsum.photos/1900/800';
  }
  @Output() private share: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private archived: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private addToWaitingList: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private borrow: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private edit: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private details: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();

  onShowDetails() {
this.details.emit(this._book);
  }

  onBorrow() {
this.borrow.emit(this._book);
  }

  onAddToWaitingList() {
this.addToWaitingList.emit(this._book);
  }

  onEdit() {
 this.edit.emit(this._book);
  }

  onShare() {
this.share.emit(this._book);
  }

  onArchived() {
this.archived.emit(this._book);
  }
}
