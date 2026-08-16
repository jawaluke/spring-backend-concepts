import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RideGroup } from '../../models/ride-group.model';

@Injectable({
  providedIn: 'root',
})
export class RideGroupService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = 'http://localhost:8080/api/groups';

  /**
   * Fetch all active groups (GET /api/groups)
   */
  getActiveGroups(): Observable<RideGroup[]> {
    return this.http.get<RideGroup[]>(this.baseUrl);
  }

  /** Add this to ride-group.service.ts */
  getGroupById(groupId: number): Observable<RideGroup> {
    return this.http.get<RideGroup>(`${this.baseUrl}/${groupId}`);
  }
  
}
