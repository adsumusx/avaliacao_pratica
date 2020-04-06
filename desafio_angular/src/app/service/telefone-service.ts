import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { config } from '../../config';
import { Page } from '../model/page';
import { PagedData } from '../model/paged-data';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { Telefone } from '../model/telefone';

const endpoint = config.apiUrlBase + '/Telefone/';
const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class TelefoneService {
    telefone: any;
    page: Page = new Page;
    constructor(private http: HttpClient) { }

    listarTelefones(page: Page): Observable<PagedData<Telefone>> {
        this.page = page;
        return this.http.get(endpoint + "listaTelefone").pipe(map((res: Response) => this.extractData(res)));
    }

    deleteTelefone(id): Observable<any> {
        return this.http.delete<any>(endpoint + 'deleteTelefone/' + id, httpOptions).pipe(

            tap(_ => `delete id=${id}`),
            catchError(this.handleError<any>('delete', null))
        );
    }

    getTelefone(id): Observable<any> {
        return this.http.get(endpoint + 'getTelefone/' + id).pipe(
            map((res: Response) => res));
    }

    updateTelefone(id, telefone): Observable<any> {
        return this.http.put(endpoint + 'putTelefone/' + id, JSON.stringify(telefone), httpOptions).pipe(
            tap(_ => console.log(`Telefone Editado`)),
            catchError(this.handleError<any>('updateTelefone'))
        );
    }

    addTelefone(Telefone): Observable<any> {
        return this.http.post<any>(endpoint + 'setTelefone', JSON.stringify(Telefone), httpOptions)
            .pipe(
                tap((Telefone) => console.log(Telefone)),
                catchError(this.handleError<any>('setTelefone', Telefone))
            );
    }



    extractData(res: Response): PagedData<Telefone> {

        let pagedData = new PagedData<Telefone>();
        if (res != null) {
            let body = res;
            this.telefone = body;
            this.page.totalElements = (this.telefone != null ? this.telefone.result_count : 0);

            if (this.telefone.result_count != null) {
                this.page.totalPages = this.page.totalElements / this.page.size;
                pagedData.result = res;
            }
            if (this.page.size != 0) {
                this.page.offset = this.page.pageNumber * this.page.size;
                this.page.limit = Math.min((this.page.offset + this.page.size), this.page.totalElements); //end
            }
            else {
                this.page.limit = this.telefone.result_count;
                this.page.offset = 0;

            }
            pagedData.data = this.telefone.results;

            pagedData.page = this.page;
        }
        return pagedData;

    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead
            // TODO: better job of transforming error for user consumption
            console.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }
}
