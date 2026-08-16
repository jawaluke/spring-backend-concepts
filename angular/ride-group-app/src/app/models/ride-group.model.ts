export interface AppUser {
  userId: number;
  userName: string;
}

export interface RideGroup {
  groupId?: number;
  groupName: string;
  destinationLat: number;
  destinationLong: number;
  status?: 'ACTIVE' | 'COMPLETED';
}

export interface GroupMembership {
  id?: number;
  userId: number;
  groupId: number;
  joinedAt?: string;
}

export interface LiveLocation {
  id?: number;
  userId: number;
  groupId: number;
  latitude: number;
  longitude: number;
  lastUpdated?: string;
}